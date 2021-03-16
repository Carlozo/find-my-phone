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