from typing import List

from schema.product import Product
from schema.transactions import Transaction
from util.data_config import NUM_PRODUCTS, NUM_COMPANIES, NUM_BANKS, NUM_TRANSACTIONS
from util.faker_config import fake


class DataGenerator:
    @staticmethod
    def generate_products() -> List[Product]:
        return [Product.generate_product() for _ in range(NUM_PRODUCTS)]

    @staticmethod
    def generate_companies() -> List[str]:
        return [fake.company() for _ in range(NUM_COMPANIES)]

    @staticmethod
    def generate_banks() -> List[str]:
        return [fake.company() for _ in range(NUM_BANKS)]

    @staticmethod
    def generate_transactions(products: List[Product], companies: List[str], banks: List[str]) -> List[Transaction]:
        return [Transaction.generate(products, companies, banks) for _ in range(NUM_TRANSACTIONS)]