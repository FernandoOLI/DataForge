from dataclasses import dataclass
from datetime import datetime, timedelta
import random
from typing import List

from schema.buyer import Buyer
from schema.card import Card
from schema.product import Product
from util.faker_config import fake


@dataclass
class Transaction:
    id: str
    created_at: str
    buyer: Buyer
    card: Card
    company: str
    item: Product
    total_value: float

    @classmethod
    def generate(cls, products: List[Product], companies: List[str], banks: List[str]) -> 'Transaction':
        purchased_item = random.choice(products)
        return cls(
            id=fake.uuid4(),
            created_at=(datetime.now() - timedelta(days=random.randint(0, 30))).strftime("%Y-%m-%d %H:%M:%S"),
            buyer=Buyer.generate(),
            card=Card.generate(banks),
            company=random.choice(companies),
            item=purchased_item,
            total_value=round(purchased_item.price, 2)
        )

    def to_dict(self) -> dict:
        """Convert Transaction object to dictionary"""
        return {
            "id": self.id,
            "created_at": self.created_at,
            "buyer": self.buyer.to_dict() if hasattr(self.buyer, 'to_dict') else vars(self.buyer),
            "card": self.card.to_dict() if hasattr(self.card, 'to_dict') else vars(self.card),
            "company": self.company,
            "item": self.item.to_dict() if hasattr(self.item, 'to_dict') else vars(self.item),
            "total_value": self.total_value
        }