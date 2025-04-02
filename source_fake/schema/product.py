from dataclasses import dataclass
from datetime import datetime, timedelta
from typing import Optional
import random
from faker import Faker

from util.categories_and_brands import CATEGORIES, BRANDS

fake = Faker("pt_BR")

@dataclass
class Product:
    """Represents a product with all its attributes"""
    name: str
    category: str
    price: float
    code: str
    sku: str
    brand: str
    stock: int
    manufacturing_at: str
    expiration_at: Optional[str] = None

    @staticmethod
    def generate_code() -> str:
        """Generate a random 13-digit product code"""
        return "".join([str(random.randint(0, 9)) for _ in range(13)])

    @classmethod
    def generate_product(cls) -> 'Product':
        """Factory method to generate a random product"""
        category = random.choice(CATEGORIES)
        name = f"{fake.word().capitalize()} {category[:-1]}"
        price = round(random.uniform(10, 5000), 2)
        code = cls.generate_code()  # Now correctly called as static method
        sku = fake.unique.ean(length=8)
        brand = random.choice(BRANDS)
        stock = random.randint(0, 500)
        manufacturing_at = fake.date_between(start_date="-2y", end_date="today").strftime("%Y-%m-%d")

        expiration_at = None
        if category == "Food":
            manufacture_date = datetime.strptime(manufacturing_at, "%Y-%m-%d")
            expiration_at = (manufacture_date + timedelta(days=random.randint(180, 730))).strftime("%Y-%m-%d")

        return cls(
            name=name,
            category=category,
            price=price,
            code=code,
            sku=sku,
            brand=brand,
            stock=stock,
            manufacturing_at=manufacturing_at,
            expiration_at=expiration_at
        )

    def to_dict(self) -> dict:
        """Convert product to dictionary representation"""
        return {
            "name": self.name,
            "category": self.category,
            "price": self.price,
            "code": self.code,
            "sku": self.sku,
            "brand": self.brand,
            "stock": self.stock,
            "manufacturing_at": self.manufacturing_at,
            "expiration_at": self.expiration_at
        }