import requests as rq
from pymongo import MongoClient

mongo = MongoClient("mongodb://root:eTDgVA5YqWayjTECpmiQdNYkgzkphg63usIswrHX@localhost:27017")
db = mongo.latinvocab
words = db.words

type_map = {
    "v": "verb",
    "n": "noun",
    "a": "adjective",
    "p": "preposition",
    "r": "pronoun",
    "d": "adverb",
    "x": "misc"
}

def word_gen():
    rsp = rq.get("https://www.exams.cambridgescp.com/files/cscp/wjec18vocab/vt.js")
    body = rsp.content.decode()
    lines = eval(body[body.index("["): body.index("]")+ 1])

    for l in lines:
        l = l.split("#")

        yield {
            "qLatin": l[0],
            "aLatin": l[3].split(":"),
            "qEnglish": l[1],
            "aEnglish": l[4].split(":"),
            "type": [type_map[char] for char in l[2]],
            "stage": int(l[5])
        }

words.insert_many(word_gen(), ordered=False)
mongo.close()
