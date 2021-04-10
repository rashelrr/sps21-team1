const list = document.getElementById('movement');

function createMovementElement() {
   const movementname = document.getElementById('movementname').value;
    document.getElementById('movementid').innerHTML = movementname;
    const movementElement = document.createElement('li');
    movementElement.appendChild(document.createTextNode(movementname));
    list.appendChild(movementElement);
}

