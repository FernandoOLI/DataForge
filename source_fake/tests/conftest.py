import pytest
from faker import Faker

@pytest.fixture
def fake():
    return Faker("pt_BR")

@pytest.fixture
def sample_product():
    from schema.product import Product
    return Product.generate_product()

@pytest.fixture
def sample_transaction(sample_product):
    from schema.transactions import Transaction
    from schema.buyer import Buyer
    from schema.card import Card

    return Transaction(
        id="test123",
        created_at="2023-01-01 12:00:00",
        buyer=Buyer.generate(),
        card=Card.generate(["Test Bank"]),
        company="Test Company",
        item=sample_product,
        total_value=100.0
    )