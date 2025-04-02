import tempfile
import os
from generator.FileGenerator import FileGenerator

class TestJsonWriter:
    def test_write_transactions(self):
        """Test writing transactions to a file"""
        test_data = [{"id": "1", "value": 100}, {"id": "2", "value": 200}]

        with tempfile.TemporaryDirectory() as temp_dir:
            FileGenerator.write_transactions(
                transactions=test_data,
                output_dir=temp_dir,
                filename="test.json"
            )

            output_file = os.path.join(temp_dir, "test.json")
            assert os.path.exists(output_file)

            with open(output_file, 'r') as f:
                lines = f.readlines()
                assert len(lines) == 2