import json
import random
from faker import Faker
from datetime import datetime, timedelta

# Create a Faker instance for generating fake data in Brazilian Portuguese
fake = Faker("pt_BR")

# Lists of categories and brands
categories = ["Electronics", "Appliances", "Clothing", "Shoes", "Food", "Furniture", "Automotive", "Sports"]
brands = ["TechMax", "SuperElectronic", "MegaFurniture", "FoodExpress", "AutoGear", "FashionStyle", "SportZone"]

# Function to generate a valid 13-digit barcode
def generate_code():
    return "".join([str(random.randint(0, 9)) for _ in range(13)])

# Function to generate a fake product
def generate_product():
    category = random.choice(categories)
    name = fake.word().capitalize() + " " + category[:-1]  # Example: "Ultra Electronic"
    price = round(random.uniform(10, 5000), 2)
    code = generate_code()
    sku = fake.unique.ean(length=8)  # 8-character SKU
    brand = random.choice(brands)
    stock = random.randint(0, 500)

    # Generate manufacturing date (within the last 2 years)
    manufacturing_at = fake.date_between(start_date="-2y", end_date="today").strftime("%Y-%m-%d")

    # If the category is "Food", generate an expiration date; otherwise, set it to None
    expiration_at = (
        (datetime.strptime(manufacturing_at, "%Y-%m-%d") + timedelta(days=random.randint(180, 730))).strftime("%Y-%m-%d")
        if category == "Food" else None
    )

    return {
        "name": name,
        "category": category,
        "price": price,
        "code": code,
        "sku": sku,
        "brand": brand,
        "stock": stock,
        "manufacturing_at": manufacturing_at,
        "expiration_at": expiration_at
    }

# Generate a fixed list of 1000 products
num_products = 1000
products = [generate_product() for _ in range(num_products)]

# Generate a list of 100 company names
num_companies = 100
companies = [fake.company() for _ in range(num_companies)]

# Generate a list of 10 bank names
num_banks = 10
banks = [fake.company() for _ in range(num_banks)]

# Function to generate a valid CPF (Brazilian tax ID)
def generate_document():
    cpf = [random.randint(0, 9) for _ in range(9)]
    for _ in range(2):
        total = sum([(len(cpf) + 1 - i) * v for i, v in enumerate(cpf)])
        digit = (total * 10) % 11
        cpf.append(digit if digit < 10 else 0)
    return "{}{}{}.{}{}{}.{}{}{}-{}{}".format(*cpf)

# Function to generate a fake transaction
def generate_transaction():
    # Generate buyer information
    buyer = {
        "name": fake.name(),
        "cpf": generate_document(),
        "phone": fake.phone_number(),
        "email": fake.email(),
        "address": fake.address().replace("\n", ", "),
        "birth_date": fake.date_of_birth(minimum_age=18, maximum_age=80).strftime("%d/%m/%Y")
    }

    # Generate card details
    card = {
        "number": fake.credit_card_number(),
        "bank": random.choice(banks),
        "agency": f"{random.randint(1000, 9999)}-{random.randint(0, 9)}",
        "account": f"{random.randint(10000, 99999)}-{random.randint(0, 9)}"
    }

    # Select a single product from the fixed product list
    purchased_item = random.choice(products)

    # Set total transaction value (since there's only one item, it's just its price)
    total_value = purchased_item["price"]

    # Create transaction details
    transaction = {
        "id": fake.uuid4(),
        "created_at": (datetime.now() - timedelta(days=random.randint(0, 30))).strftime("%Y-%m-%d %H:%M:%S"),
        "buyer": buyer,
        "card": card,
        "company": random.choice(companies),  # Assign the transaction to a random company
        "item": purchased_item,  # Only one item per transaction
        "total_value": round(total_value, 2)
    }

    return transaction

# Generate 10,000 transactions
num_transactions = 2
transactions = [generate_transaction() for _ in range(num_transactions)]

# Save transactions to a JSON file
with open("../data/source/transactions.json", "w", encoding="utf-8") as f:
    for transaction in transactions:
        f.write(json.dumps(transaction, ensure_ascii=False) + "\n")

