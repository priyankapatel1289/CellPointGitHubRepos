# CellPoint GitHub Repositories

The attempt of the project was to use RXJava, Apollo for Android GraphQL and Realm to make this project. 

I have not been able to complete the project or use any of the above required libraries due to lack of resources and time constraint. I have made an attempt to manually parse json data from GitHub API using AsyncTask. 

My plan was to use HashSet to separate unique languages of the repositories and create a list which can be used as the header of the expandableListView. HashMap would be to used for childView of each header which would match is using key value pairs of language and repository name. 

Once the repositories show up in child views, intents would be used along with parcelable to send data from one activity to another. 