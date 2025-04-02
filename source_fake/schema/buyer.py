from dataclasses import dataclass
import random

from util.faker_config import fake

@dataclass
class Buyer:
    name: str
    cpf: str
    phone: str
    email: str
    address: str
    birth_date: str

    @classmethod
    def generate(cls) -> 'Buyer':
        return cls(
            name=fake.name(),
            cpf=DocumentGenerator.generate_cpf(),
            phone=fake.phone_number(),
            email=fake.email(),
            address=fake.address().replace("\n", ", "),
            birth_date=fake.date_of_birth(minimum_age=18, maximum_age=80).strftime("%d/%m/%Y")
        )

class DocumentGenerator:
    @staticmethod
    def generate_cpf() -> str:
        cpf = [random.randint(0, 9) for _ in range(9)]
        for _ in range(2):
            total = sum([(len(cpf) + 1 - i) * v for i, v in enumerate(cpf)])
            digit = (total * 10) % 11
            cpf.append(digit if digit < 10 else 0)
        return "{}{}{}.{}{}{}.{}{}{}-{}{}".format(*cpf)