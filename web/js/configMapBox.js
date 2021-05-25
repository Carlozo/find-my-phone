let db = firebase.firestore();
let lat;
let lgn;
let userId = "G3CCmwCEv4XoE4NdyvZTrZdumxD2";

function usuarioLogado(){
    firebase.auth().onAuthStateChanged(function(user) {
      if (user) {
          var user = firebase.auth().currentUser;
          userId = user.uid;
          console.log(userId);
      } else {
      
      }
    }); 
  }

db.collection("localization").where("id", "==", userId)
    .get()
    .then((querySnapshot) => {
        querySnapshot.forEach((doc) => {
            lat = doc.data().localizacao.latitude;
            lgn = doc.data().localizacao.longitude;
            console.log(lat,lgn);
            console.log(doc.data());
        });
    })
    .catch((error) => {
        console.log("Error getting documents: ", error);
    });


