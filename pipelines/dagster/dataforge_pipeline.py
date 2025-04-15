import subprocess
from dagster import op, job

@op
def gerar_csv():
    subprocess.run(["python3", "source_fake/Main.py"], check=True)

@op
def enviar_para_kafka():
    subprocess.run(["java", "-jar", "generateData/sendDataTokafka/target/sendDataTokafka-1.0-SNAPSHOT.jar"], check=True)

@op
def camada_bronze():
    subprocess.run(["sbt", "run"], cwd="batch/bronze/src/main/scala/Main.scala", check=True)

@op
def camada_prata():
    subprocess.run(["sbt", "run"], cwd="batch/silver/src/main/scala/Main.scala", check=True)

@op
def exportar_para_mongo():
    subprocess.run(["sbt", "run"], cwd="batch/exportDeltaToMongo/src/main/scala/Main.scala", check=True)

@job
def pipeline_dataforge():
    gerar_csv()
    enviar_para_kafka()
    camada_bronze()
    camada_prata()
    exportar_para_mongo()
