function logar(){
    let entrarEmail = document.getElementById("entrarEmail").value;
    let entrarSenha = document.getElementById("entrarSenha").value;

    firebase.auth().signInWithEmailAndPassword(entrarEmail, entrarSenha)
        .then((user) => {
        window.location.href = "mapa.html";
    })
        .catch((error) => {
        var errorCode = error.code;
        var errorMessage = error.message;
        window.alert("senha incorreta ou usuário inexistente!");
     });
}

function sair(){
    const sair = document.getElementById("sair").value;
    firebase.auth().signOut()
    .then((user) => {
      console.log('usuário deslogado');
      window.location.href = "index.html";
    });
}

function inscreverse(){
    let criarEmail = document.getElementById("criarEmail").value;
    let criarSenha = document.getElementById("criarSenha").value;
    
    firebase.auth().createUserWithEmailAndPassword(criarEmail, criarSenha)
  .then((user) => {
    console.log(user);
    window.alert("conta criada com sucesso!");
    window.location.href = "index.html";
  })
  .catch((error) => {
    var errorCode = error.code;
    var errorMessage = error.message;
    window.alert("erro ao cadastrar conta!");
  });
}

function alterarSenha(){
  let resetEmail = document.getElementById("resetEmail").value;
  
  firebase.auth().sendPasswordResetEmail(resetEmail).then(function() {
    // Email sent.
    window.alert("E-mail enviado!");
  }).catch(function(error) {
    // An error happened
    window.alert("E-mail incorreto ou não cadastrado!");
  });
}

