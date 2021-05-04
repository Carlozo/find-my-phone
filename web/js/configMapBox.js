let lat;
let lgn;
let userId = "eFx6q1O9C2Wz3lBzW6ZbnRsm0D52";

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


mapboxgl.accessToken = 'pk.eyJ1Ijoid2FnbmVybWFycXVlcyIsImEiOiJjanZlZnllNDQwamczNGRwbmM3a3lxN3UyIn0.2IrgrK7peJF9P2oxhDVwTg';

navigator.geolocation.getCurrentPosition(position => {
    let instanciaMapa = {
        container: 'mapa',
        style: 'mapbox://styles/mapbox/streets-v11',
        center: [lat,lgn],
        zoom: 12
    };
    let mapa = new mapboxgl.Map(instanciaMapa);

    let marcador = {
        color: 'red'
    };

    let posicaoAtual = new mapboxgl.Marker(marcador);
    posicaoAtual.setLngLat([lat,lgn]);
    posicaoAtual.addTo(mapa);
});
