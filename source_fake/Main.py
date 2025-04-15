# Usage example:
from generator.DataGenerator import DataGenerator
from generator.FileGenerator import FileGenerator

def main():
    products = DataGenerator.generate_products()
    companies = DataGenerator.generate_companies()
    banks = DataGenerator.generate_banks()
    transactions = DataGenerator.generate_transactions(products, companies, banks)

    FileGenerator.write_transactions(
        transactions=[t.to_dict() for t in transactions],
        output_dir="../data/source",
        filename="transactionss.json")

if __name__ == "__main__":
    main()
