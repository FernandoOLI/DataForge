from schema.buyer import Buyer

class TestBuyerGeneration:
    def test_buyer_creation(self):
        buyer = Buyer.generate()

        assert isinstance(buyer.name, str)
        assert len(buyer.cpf) == 14  # Format: 000.000.000-00
        assert "@" in buyer.email
        assert "," in buyer.address  # From your address formatting