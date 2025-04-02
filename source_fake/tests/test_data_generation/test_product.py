from schema.product import Product
from util.categories_and_brands import CATEGORIES
from unittest import mock

class TestProductGeneration:
    def test_product_creation(self):
        """Test that a product can be generated with all required fields"""
        product = Product.generate_product()

        assert isinstance(product.name, str)
        assert product.category in CATEGORIES
        assert 10 <= product.price <= 5000
        assert len(product.code) == 13
        assert product.code.isdigit()
        assert len(product.sku) == 8
        assert product.stock >= 0

    def test_food_product_expiration(self):
        """Test that food products get expiration dates"""
        # We need to mock the random choice to always return "Food"
        with mock.patch('random.choice', return_value="Food"):
            product = Product.generate_product()
            assert product.expiration_at is not None