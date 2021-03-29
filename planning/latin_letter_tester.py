import numpy as np
import pandas as pd
from IPython.display import clear_output as co

df = pd.read_csv("latin-vocab.csv")

while True:
    co(True)
    rawOpt = input("Enter comma seperated letters of words to test on or all e.g. (a,b,c/all): ").lower()
    if "all" in rawOpt:
        idxs = np.arange(df.shape[0])
    else:
        opt = rawOpt.replace(" ", "").split(",")

        idxs = []
        for i in range(df.shape[0]):
            if df.loc[i, "word"][0] in opt:
                idxs.append(i)

        idxs = np.array(idxs, dtype=np.int)
    np.random.shuffle(idxs)

    for idx in idxs:
        co(True)
        attempt = input(f"{df.loc[idx, 'word']}: ").lower()
        if attempt in df.loc[idx, "definition"].lower():
            print("Correct! ", end="")
        else:
            print("Nope! ", end="")
        print(f"Here's the official definition: {df.loc[idx, 'definition']}")

    # Continue / quit
    co(True)
    if "y" in input("All done! Do you want to quit? (Y/n): ").lower():
        break
