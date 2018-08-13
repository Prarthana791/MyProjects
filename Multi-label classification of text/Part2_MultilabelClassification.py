import pandas as pd
import numpy as np
import csv
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.model_selection import train_test_split, cross_val_score
from sklearn.metrics import accuracy_score
from skmultilearn.problem_transform import BinaryRelevance
from sklearn.naive_bayes import GaussianNB
from skmultilearn.problem_transform import ClassifierChain
from skmultilearn.problem_transform import LabelPowerset
from skmultilearn.adapt import MLkNN

#This method builds the model and return it
def QuesSubTypeModel(TrainingDatafilepath,Outfilepath):
    df = pd.read_csv(TrainingDatafilepath,header=0,encoding = "ISO-8859-1")

    train,test = train_test_split(df)
    train = train.dropna()
    test = test.dropna()
    
    vectorizer = TfidfVectorizer()
    X_train = vectorizer.fit_transform(train.iloc[:,0])
    Y_train = train.iloc[:,1:4]

    ##############I have experimented 3 types of modelling method for multi-label classification###################
    ##############1. Binary Relevance method, 2. Classifier chain and 3.Label powerset. As the label powerset provides highest accuracy of ~70%, I choose that.#####
    ##############You can uncomment any of the methoda and verify the result################

    ##gaussian naive bayes base classifier
    #classifier = BinaryRelevance(GaussianNB())
    #classifier.fit(X_train,Y_train)

    #X_test = vectorizer.transform( test.iloc[:,0] )
    #predictions = classifier.predict(X_test)
    
    #accuracy = accuracy_score(predictions,np.array(test.iloc[:,1:4]))
    #print("Binary relevance " + str(accuracy))

    ##classifier chain
    #classifier = ClassifierChain(GaussianNB())
    #classifier.fit(X_train,Y_train)

    #X_test = vectorizer.transform( test.iloc[:,0] )
    #predictions = classifier.predict(X_test)
    
    #accuracy = accuracy_score(predictions,np.array(test.iloc[:,1:4]))
    #print("Classifier chain " + str(accuracy))

    #Label powerset
    classifier = LabelPowerset(GaussianNB())
    classifier.fit(X_train,Y_train)

    X_test = vectorizer.transform( test.iloc[:,0] )
    predictions = classifier.predict(X_test)
    
    accuracy = accuracy_score(predictions,np.array(test.iloc[:,1:4]))
    print("Label Powerset " + str(accuracy))

        
    with open(Outfilepath,'w', encoding = "ISO-8859-1") as ofile:
        writer = csv.writer(ofile,delimiter = '\t',lineterminator='\n')
        writer.writerow(["Text","Animals","Countries","Famous Personalities"])
        for index in range(predictions.shape[0]):
            writer.writerow([np.array(test.iloc[:,0])[index],predictions[index,0],predictions[index,1],predictions[index,2],test.iloc[index,1],test.iloc[index,2],test.iloc[index,3]])

    return classifier, vectorizer
subtypesmodel, modelVector = QuesSubTypeModel('E:\Others\jobs\Learning\Ask\QuesSubtypes.csv','E:\Others\jobs\Learning\Ask\QuesSubtypesModelResult.tsv')

#This method use the above model to do the prediction
def QuesSubType(Infilepath,Outfilepath):
    df = pd.read_csv(Infilepath,header=0,encoding = "ISO-8859-1")
    df = df.dropna()

    
    #vectorizer = TfidfVectorizer()
    df_vector = modelVector.transform(df.iloc[:,0])
    
    actual_predictions = subtypesmodel.predict(df_vector)
    
    with open(Outfilepath,'w', encoding = "ISO-8859-1") as ofile:
        writer = csv.writer(ofile,delimiter = '\t',lineterminator='\n')
        writer.writerow(["Text","Animals","Countries","Famous Personalities"])
        for index in range(actual_predictions.shape[0]):
            writer.writerow([np.array(df.iloc[:,0])[index],actual_predictions[index,0],actual_predictions[index,1],actual_predictions[index,2]])
           
QuesSubType('E:\Others\jobs\Learning\Ask\QuesSubtypes.csv','E:\Others\jobs\Learning\Ask\QuesSubtypeResult.tsv')

