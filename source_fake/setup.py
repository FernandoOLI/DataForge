from setuptools import setup, find_packages


def read_requirements():
    with open('requirements.txt') as f:
        return f.read().splitlines()


setup(
    name='source_fake',
    version='0.1.0',
    packages=find_packages(),
    install_requires=read_requirements(),
    author='Fernando',
    description='Pacote para ingestão fake de dados no DataForge',
    python_requires='>=3.7',
)
