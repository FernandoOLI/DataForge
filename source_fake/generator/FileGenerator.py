import json
from pathlib import Path
from typing import List, Dict, Any

class FileGenerator:
    """Handles writing data to JSON files with proper error handling and file management"""

    @staticmethod
    def write_transactions(transactions: List[Dict[str, Any]],
                           output_dir: str = "../data/source",
                           filename: str = "transactions.json") -> None:
        """
        Write transactions to a JSON file (one JSON object per line)
        
        Args:
            transactions: List of transaction dictionaries
            output_dir: Directory to save the file
            filename: Name of the output file
        """
        try:
            output_path = Path(output_dir)

            # Ensure directory exists
            output_path.mkdir(parents=True, exist_ok=True)

            file_path = output_path / filename

            with open(file_path, "w", encoding="utf-8") as f:
                for transaction in transactions:
                    json_line = json.dumps(transaction, ensure_ascii=False)
                    f.write(f"{json_line}\n")

            print(f"Successfully wrote {len(transactions)} transactions to {file_path}")

        except (IOError, OSError) as e:
            print(f"Error writing transactions file: {e}")
            raise
        except json.JSONEncodeError as e:
            print(f"Error encoding transaction data: {e}")
            raise
        except Exception as e:
            print(f"Unexpected error: {e}")
            raise

