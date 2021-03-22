//import firebase from "firebase/app";
//import "firebase/auth";

function entrarFacebook(){
  var provider = new firebase.auth.FacebookAuthProvider();

  firebase
  .auth()
  .signInWithRedirect(provider)
  .then((result) => {
    /** @type {firebase.auth.OAuthCredential} */
    var credential = result.credential;

    // The signed-in user info.
    var user = result.user;

    // This gives you a Facebook Access Token. You can use it to access the Facebook API.
    var accessToken = credential.accessToken;

    // ...
  })
  .catch((error) => {
    // Handle Errors here.
    var errorCode = error.code;
    var errorMessage = error.message;
    // The email of the user's account used.
    var email = error.email;
    // The firebase.auth.AuthCredential type that was used.
    var credential = error.credential;

    window.alert(errorCode,errorMessage,email,credential);
    // ...
  });
}

function entrarGoogle(){

  var provider = new firebase.auth.GoogleAuthProvider();

  window.alert(provider);
  firebase.auth()
  .signInWithRedirect(provider)
  .then((result) => {
    /** @type {firebase.auth.OAuthCredential} */
    var credential = result.credential;
    // This gives you a Google Access Token. You can use it to access the Google API.
    var token = credential.accessToken;
    // The signed-in user info.
    var user = result.user;
    // ...
  }).catch((error) => {
    // Handle Errors here.
    var errorCode = error.code;
    var errorMessage = error.message;
    // The email of the user's account used.
    var email = error.email;
    // The firebase.auth.AuthCredential type that was used.
    var credential = error.credential;
    // ...
    window.alert(errorCode,errorMessage,email,credential);
  });
}

function logar(){
    let entrarEmail = document.getElementById("entrarEmail").value;
    let entrarSenha = document.getElementById("entrarSenha").value;

    firebase.auth().signInWithEmailAndPassword(entrarEmail, entrarSenha)
        .then((user) => {
        window.alert("conta logada com sucesso!");
        window.location.href = "https://projetointegrado-etec2021.herokuapp.com/mapa.html";
    })
        .catch((error) => {
        var errorCode = error.code;
        var errorMessage = error.message;
        window.alert("senha incorreta ou usuário inexistente!");
     });
}

function inscreverse(){
    let criarEmail = document.getElementById("criarEmail").value;
    let criarSenha = document.getElementById("criarSenha").value;
    
    firebase.auth().createUserWithEmailAndPassword(criarEmail, criarSenha)
  .then((user) => {
    console.log(user);
    window.alert("conta criada com sucesso!");
    window.location.href = "https://projetointegrado-etec2021.herokuapp.com/index.html";
  })
  .catch((error) => {
    var errorCode = error.code;
    var errorMessage = error.message;
    window.alert("erro ao cadastrar conta!");
  });
}



function usuarioLogado(){
    try{
        let u = firebase.auth().currentUser;
        return u.entrarEmail;
    }catch(e){
        return e;
    }
}