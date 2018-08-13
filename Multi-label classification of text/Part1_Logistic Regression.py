import pandas as pd
import numpy as np
import csv
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.linear_model.logistic import LogisticRegression
from sklearn.model_selection import train_test_split, cross_val_score
from sklearn.metrics import accuracy_score

#This method builds the model and return it
def IsQuesModel(TrainingDatafilepath,Outfilepath):
    df = pd.read_csv(TrainingDatafilepath,header=0,encoding = "ISO-8859-1")

    train,test = train_test_split(df)
    train = train.dropna()
    test = test.dropna()
    
    vectorizer = TfidfVectorizer()
    X_train = vectorizer.fit_transform(train.iloc[:,0])
    Y_train = train.iloc[:,1]

    #Model is buid using logistic regression method. Accuracy achieved is ~97%
    classifier = LogisticRegression()
    classifier.fit(X_train,Y_train )

    X_test = vectorizer.transform( test.iloc[:,0] )
    predictions = classifier.predict(X_test)
    accuracy = accuracy_score(predictions,np.array(test.iloc[:,1]))
    print(accuracy)

    with open(Outfilepath,'w', encoding = "ISO-8859-1") as ofile:
        writer = csv.writer(ofile,delimiter = '\t',lineterminator='\n')
        writer.writerow(["Text","Code"])
        for index in range(len(predictions)):
            writer.writerow([np.array(test.iloc[:,0])[index],predictions[index]])
    return classifier,vectorizer
Quesmodel,modelVector = IsQuesModel('E:\Others\jobs\Learning\Ask\quesDataShuffledWithoutsymbols.csv','E:\Others\jobs\Learning\Ask\quesDataModelResult.tsv')#quesDataShuffled i/p

#This method use the above model to do the prediction
def IsQues(Infilepath,Outfilepath):
    df = pd.read_csv(Infilepath,header=0,encoding = "ISO-8859-1")
    df = df.dropna()

    df_vector = modelVector.transform(df.iloc[:,0])
    
    actual_predictions = Quesmodel.predict(df_vector)
    
    with open(Outfilepath,'w', encoding = "ISO-8859-1") as ofile:
        writer = csv.writer(ofile,delimiter = '\t',lineterminator='\n')
        writer.writerow(["Text","Code"])
        for index in range(len(actual_predictions)):
            writer.writerow([np.array(df.iloc[:,0])[index],actual_predictions[index]])
           
IsQues('E:\Others\jobs\Learning\Ask\AskData.csv','E:\Others\jobs\Learning\Ask\QuesResult.tsv')