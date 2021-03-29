import psycopg2 as pg
import pandas as pd

df = pd.read_csv("latin-vocab.csv")

conn = pg.connect("postgresql://root@localhost:26257/latin")
cur = conn.cursor()
cur.executemany("INSERT INTO vocab (latin, details, english, grammar, stage) VALUES (%s, %s, %s, %s, %s);", df.to_numpy())
conn.commit()
print("done")