


function criarConta(){

    email = document.getElementById(email);
    password = document.getElementById(senha);

    firebase.auth().createUserWithEmailAndPassword(email, password)
    .then((user) => {
      alert("Foi");
      // ...
    })
    .catch((error) => {
      var errorCode = error.code;
      var errorMessage = error.message;

      alert(errorCode,errorMessage);
      // ..
    });
} 

function entrarComConta(){

  email = document.getElementById(email);
  password = document.getElementById(senha);  

  firebase.auth().signInWithEmailAndPassword(email, password)
  .then((user) => {
    alert("Signed in");
    // ...
  })
  .catch((error) => {
    var errorCode = error.code;
    var errorMessage = error.message;

    alert(errorCode,errorMessage);  
  });
}

/*  firebase.auth().onAuthStateChanged((user) => {
    if (user) {
      // User is signed in, see docs for a list of available properties
      // https://firebase.google.com/docs/reference/js/firebase.User
      var uid = user.uid;
      // ...
    } else {
      // User is signed out
      // ...
    }
  });*/