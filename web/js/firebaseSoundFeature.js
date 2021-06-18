let db = firebase.firestore();

function soundAble(){

let user = firebase.auth().currentUser;
let documentReference = db.collection("localization").doc(user.uid);

return documentReference.update({
    sound: true
})
.then(() => {
    console.log("Document successfully updated!");
})
.catch((error) => {
    console.log("Error updating document: ", error);
});
}