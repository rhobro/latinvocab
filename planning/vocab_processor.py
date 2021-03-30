import requests as rq
from pymongo import MongoClient

mongo = MongoClient("mongodb://root:eTDgVA5YqWayjTECpmiQdNYkgzkphg63usIswrHX@localhost:27017")
db = mongo.latinvocab
words = db.words

def word_gen():
    rsp = rq.get("https://www.exams.cambridgescp.com/files/cscp/wjec18vocab/vt.js")
    lines = eval(rsp.text[rsp.text.index("["): rsp.text.index("]")+ 1])

    for l in lines:
        l = l.split("#")

        yield {
            "qLatin": l[0],
            "aLatin": l[3].split(":"),
            "qEnglish": l[1],
            "aEnglish": l[4].split(":"),
            "type": l[2],
            "stage": int(l[5])
        }

words.insert_many(word_gen(), ordered=False)
mongo.close()
