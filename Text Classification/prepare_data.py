import codecs
import csv
import re
import string

import pandas as pd
import numpy as np
import nltk
from nltk.corpus import wordnet
from nltk.corpus import stopwords

txt_file = r'D:\Studia\Sem6\Sztuczna Inteligencja\Laby\lab4\booksummaries\booksummaries.txt'
fixed_txt = r'D:\Studia\Sem6\Sztuczna Inteligencja\Laby\lab4\booksummaries\booksummariesfixed.txt'
csv_file = r'D:\Studia\Sem6\Sztuczna Inteligencja\Laby\lab4\booksummaries\bookSummaries.csv'

data_file_delimiter = '\t'


def replace_semicolon_in_txt():
    with open(txt_file, 'r', encoding="utf8") as file:
        file_data = file.read()

    file_data = file_data.replace(';', ',')

    with open(fixed_txt, 'w', encoding="utf8") as file:
        file.write(file_data)


def delete_unnecessary_columns():
    data = pd.read_csv(csv_file, encoding="ISO-8859-1", sep=";")
    data.drop('freebaseId', inplace=True, axis=1)
    data.drop('title', inplace=True, axis=1)
    data.drop('author', inplace=True, axis=1)
    data.drop('publicationDate', inplace=True, axis=1)
    data.to_csv(csv_file, index=None, header=True, sep=';', encoding="ISO-8859-1")
    print(data)


def delete_rows_with_empty_genres():
    df = pd.read_csv(
        r'D:\Studia\Sem6\Sztuczna Inteligencja\Laby\lab4\booksummaries\bookSummariesWithDeletedColumns.csv',
        encoding="ISO-8859-1", sep=";")

    vector_not_null = df['genres'].notnull()
    df_not_null = df[vector_not_null]

    df_not_null.to_csv(csv_file, index=None, header=True, sep=';', encoding="ISO-8859-1")


def delete_rows_with_short_summary():
    df = pd.read_csv(csv_file, encoding="ISO-8859-1", sep=";")
    df = df[df["summary"].str.len() > 60]
    df.to_csv(csv_file, index=None, header=True, sep=';', encoding="ISO-8859-1")


def split_by_colon():
    df = pd.read_csv(csv_file, encoding="ISO-8859-1", sep=";")

    f = lambda x: x["genres"].split(":")[1]
    df["genres"] = df.apply(f, axis=1)

    df.to_csv(csv_file, index=None, header=True, sep=';', encoding="ISO-8859-1")


def split_by_comma():
    df = pd.read_csv(csv_file, encoding="ISO-8859-1", sep=";")

    f = lambda x: x["genres"].split(",")[0]
    df["genres"] = df.apply(f, axis=1)

    df.to_csv(csv_file, index=None, header=True, sep=';', encoding="ISO-8859-1")


def extract_genres():
    df = pd.read_csv(csv_file, encoding="ISO-8859-1", sep=";")

    f = lambda x: x["genres"].split("\"")[1]
    df["genres"] = df.apply(f, axis=1)

    df.to_csv(csv_file, index=None, header=True, sep=';', encoding="ISO-8859-1")


def count_unique_genres():
    df = pd.read_csv(csv_file, encoding="ISO-8859-1", sep=";")
    print(df["genres"].value_counts().head(20))


def filter_by_chosen_genres():
    data = pd.read_csv(csv_file, encoding="ISO-8859-1", sep=";")
    data = data.loc[(data["genres"] == "Science Fiction") | (data["genres"] == "Speculative fiction") |
                    (data["genres"] == "Children's literature") | (data["genres"] == "Novel") |
                    (data["genres"] == "Mystery") | (data["genres"] == "Crime Fiction") |
                    (data["genres"] == "Fantasy") | (data["genres"] == "Thriller")]

    data.to_csv(csv_file, index=None, header=True, sep=';', encoding="ISO-8859-1")


def display_prepared_data(file):
    dataframe = pd.read_csv(file, encoding="ISO-8859-1", sep=";")
    print("Labels: ")
    print(dataframe["genres"].value_counts())

    avg = (dataframe.groupby('genres')['summary'].apply(lambda x: np.mean(x.str.len())))
    print("\nAverage:\n", avg)
    print("\nData:")

    for index, line in dataframe.iterrows():
        print(line)
        if index > 10:
            break


def to_lower_case():
    dataframe = pd.read_csv(csv_file, encoding="ISO-8859-1", sep=";", dtype=str).apply(
        lambda x: x.astype(str).str.lower())
    dataframe.to_csv(csv_file, index=None, header=True, sep=';', encoding="ISO-8859-1")


def get_wordnet_pos(word):
    tag = nltk.pos_tag([word])[0][1][0].upper()
    tag_dict = {"J": wordnet.ADJ,
                "N": wordnet.NOUN,
                "V": wordnet.VERB,
                "R": wordnet.ADV}

    return tag_dict.get(tag, wordnet.NOUN)


def lemmatisation():
    dataframe = pd.read_csv(csv_file, encoding="ISO-8859-1", sep=";", dtype=str)
    lemmatizer = nltk.WordNetLemmatizer()

    for index, line in dataframe.iterrows():
        s = [lemmatizer.lemmatize(w, get_wordnet_pos(w)) for w in nltk.word_tokenize(line['summary'])]
        dataframe.loc[index, 'summary'] = s

    dataframe.to_csv(csv_file, index=None, header=True, sep=';', encoding="ISO-8859-1")


def delete_punctuation_marks():
    dataframe = pd.read_csv(csv_file, encoding="ISO-8859-1", sep=";", dtype=str)

    punctuation_marks_table = str.maketrans({key: None for key in string.punctuation})

    for index, line in dataframe.iterrows():
        newLine = str(line['summary']).translate(punctuation_marks_table)
        dataframe.loc[index, 'summary'] = newLine

    dataframe.to_csv(csv_file, index=None, header=True, sep=';', encoding="ISO-8859-1")


def delete_stop_words():
    dataframe = pd.read_csv(csv_file, encoding="ISO-8859-1", sep=";", dtype=str)

    stop_words = set(nltk.corpus.stopwords.words('english'))
    for index, line in dataframe.iterrows():
        currLine = nltk.word_tokenize(line['summary'])
        filteredSentence = []

        for w in currLine:
            if w not in stop_words:
                filteredSentence.append(w)

        dataframe.loc[index, 'summary'] = filteredSentence

    dataframe.to_csv(csv_file, index=None, header=True, sep=';', encoding="ISO-8859-1")


def delete_unnecessary_marks():
    dataframe = pd.read_csv(csv_file, encoding="ISO-8859-1", sep=";", dtype=str)

    stop_words = ['x90', 'x91', 'x92', 'x93', 'x94', 'x95', 'x96', 'x97', 'x98', 'x99']
    for index, line in dataframe.iterrows():
        currLine = nltk.word_tokenize(line['summary'])
        filteredSentence = []

        for w in currLine:
            if w not in stop_words:
                filteredSentence.append(w)

        dataframe.loc[index, 'summary'] = filteredSentence

    dataframe.to_csv(csv_file, index=None, header=True, sep=';', encoding="ISO-8859-1")
