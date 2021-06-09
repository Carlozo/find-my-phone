let lat;
let lgn;
let userId;

firebase.auth().onAuthStateChanged(function(user) {
    if (user) {
        userId = user.uid;
        console.log(userId);
        db.collection("localization").where("id", "==", userId)
        .get()
        .then((querySnapshot) => {
            querySnapshot.forEach((doc) => {
                lat = doc.data().localizacao.latitude;
                lgn = doc.data().localizacao.longitude;
                instanciarMapa(lat,lgn);
            });
        })
        .catch((error) => {
            console.log("Error getting documents: ", error);
        });
        
    } else {
        console.log(user);
    }
});

