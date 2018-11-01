# UnaiMarvel

This application starts with a splashview during two seconds and then display a view with content of Hero of Marvel.

The app starts on MainActivity where I load a splash bitmap during two seconds, when the two secons over then I show
the main view, where I show a spinner during the request of Marvel Servers. So the splashview and mainview are in the same
activity, so I have to do a "tricky" to show during two seconds the splash hiding the content view and then hide off the 
mainview.

Anyway, when the MainActivity starts I init some main views and also, I make the injection (with dagger2) of the ConnectionManager
service, to make the calls to server.

The calls to server are in background, and the user only see a progressbar (the user MUST have network connection, I didn't
control the case...) , when I receive the first call , that is the information of the hero , then I call a Factory class 
(that it's singelton) and convert the models of server to app models (to can work better in the app, and control future 
changes on Marvel side...) . When I have the factory done, then I send the information to the activity, and I populate the 
data. But during this process I also need to make two more request... Events, and Comics, that the url is on the answer of the 
first request. So, during the first factory of information I throw two requests in background to get these event and comics 
information. The process is the same, when comics or events are ready, I send the information to the activity and I populate 
the list of data (with recyclerview). 

I separate the project in different packages .. LOGIC (factory and injection classes) , MODELS (server models and client models)
and SERVERCONNECTION(Implementation of the different request),VIEWS ( adapter of recyclerview) and then you have the MainActivity that works with this 
classes, 

VIEWS:

I use relativelayout as parent view of the app, inside I have ImageView to show the hero image loading the url image with 
GLIDE. 
The three buttons open an external browser to get more information.
The selection of comics or events list is done with TabLayout and I use the same recyclerview under this TabLayout, I also
add progressbar during the load of information of events and comics.
The views and design are simple, I use solid colors and I custom the background drawable button to be cooler.

CONNECTION

To have access to Marvel server, I have a public api key, private key and I have to generate hash with md5, so I use 
external library called Hasher, and when I have the hash I create the request with httpclient . And then the answer I 
deserialize the string with external library called GSON to ServerModels, and then I call the factory to generate Client Models.
I do three request in total, profile hero , comics hero, events hero, both of three are getters.



The injection of ConectionManager is done because is required on the description of this project, but probably I do it different, 
is only to show that I have the knowledge of injection....
The texts of the app (texts and keys) I didn’t' add it to strings...I have to...but no time!Also the code is not commented 
at all ..
I spend more or less one day doing this project.


In this application I used different libraries
