from dataclasses import dataclass
import random
from typing import List

from util.faker_config import fake

@dataclass
class Card:
    number: str
    bank: str
    agency: str
    account: str

    @classmethod
    def generate(cls, banks: List[str]) -> 'Card':
        return cls(
            number=fake.credit_card_number(),
            bank=random.choice(banks),
            agency=f"{random.randint(1000, 9999)}-{random.randint(0, 9)}",
            account=f"{random.randint(10000, 99999)}-{random.randint(0, 9)}"
        )