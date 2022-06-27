import nltk
import numpy as np
from sklearn.feature_extraction.text import TfidfTransformer
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.naive_bayes import MultinomialNB
from sklearn.model_selection import GridSearchCV
from sklearn.linear_model import LogisticRegression
from sklearn.ensemble import RandomForestClassifier
from sklearn.svm import LinearSVC, SVC
from sklearn.model_selection import train_test_split
from sklearn.model_selection import KFold
import sklearn.metrics as metrics
import matplotlib.pyplot as plt
import pandas as pd
import seaborn as sns
from sklearn import datasets
from sklearn.feature_selection import chi2
from sklearn.feature_selection import SelectKBest
from sklearn.utils import class_weight

csv_file = r'D:\Studia\Sem6\Sztuczna Inteligencja\Laby\lab4\booksummaries\bookSummaries.csv'


def extract_features():
    dataframe = pd.read_csv(csv_file, encoding="ISO-8859-1", sep=";", dtype=str)

    count_vect = CountVectorizer()
    tfidf_transformer = TfidfTransformer()

    X = dataframe['summary']
    X_cost = count_vect.fit_transform(X)
    X_tfidf = tfidf_transformer.fit_transform(X_cost)

    y = dataframe['genres']
    return X_tfidf, y


def select_features():
    X = extract_features()[0]
    y = extract_features()[1]

    k = 35000
    test = SelectKBest(score_func=chi2, k=k)
    X_new = test.fit_transform(X, y)

    return X_new, y


def save_train_and_test_data():
    X, y = select_features()
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.1, shuffle=True)

    return X_train, X_test, y_train, y_test


def show_naive_bayes():
    X_train, X_test, y_train, y_test = save_train_and_test_data()

    sample = class_weight.compute_sample_weight('balanced', y_train)

    param_grid = {"alpha": [0.1],
                  "fit_prior": [True]}

    grid_search = GridSearchCV(MultinomialNB(), param_grid, verbose=2, cv=10)
    grid_search.fit(X_train, y_train, sample_weight=sample)

    print(grid_search.best_params_)
    print(grid_search.best_score_)
    print(grid_search.score(X_test, y_test))
    y_predict = grid_search.predict(X_test)
    print("Bayes Accuracy %.3f" % metrics.accuracy_score(y_test, y_predict))
    print("F1 score %.3f" % metrics.f1_score(y_test, y_predict, average='weighted'))

    conf_mat = metrics.confusion_matrix(y_test, y_predict)
    fig, ax = plt.subplots(figsize=(10, 10))
    list_set = set(y_predict)
    labels = (list(list_set))
    sns.heatmap(conf_mat.T, square=True, annot=True, fmt='d', cbar=False)
    plt.ylabel('Actual')
    plt.xlabel('Predicted')
    plt.show()


def show_SVM():
    X_train, X_test, y_train, y_test = save_train_and_test_data()
    # param_grid = {"C": [100], 'gamma': [1], 'kernel': ['rbf'], 'degree': [2]}
    # grid_search = GridSearchCV(SVC(class_weight='balanced'), param_grid, verbose=2, cv=10)
    # grid_search.fit(X_train, y_train)

    param_grid = {"C": [100], "max_iter": [4000]}
    grid_search = GridSearchCV(LinearSVC(class_weight='balanced'), param_grid, verbose=2, cv=10)
    grid_search.fit(X_train, y_train)

    print(grid_search.best_params_)
    print(grid_search.best_score_)
    print(grid_search.score(X_test, y_test))
    y_predict = grid_search.predict(X_test)
    print("SVC Accuracy %.3f" % metrics.accuracy_score(y_test, y_predict))
    print("F1 score %.3f" % metrics.f1_score(y_test, y_predict, average='weighted'))

    conf_mat = metrics.confusion_matrix(y_test, y_predict)
    fig, ax = plt.subplots(figsize=(10, 10))
    list_set = set(y_predict)
    labels = (list(list_set))
    sns.heatmap(conf_mat.T, square=True, annot=True, fmt='d', cbar=False, xticklabels=labels, yticklabels=labels)
    plt.ylabel('Actual')
    plt.xlabel('Predicted')
    plt.show()
