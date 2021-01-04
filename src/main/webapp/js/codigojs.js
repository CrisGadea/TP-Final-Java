let ponemosALaEscuchaLosInput = () => {
    let titulo = document.getElementById("titulo");
    let estreno = document.getElementById("fecha-estreno");
    let descripcion = document.getElementById("descripcion");
    let genero = document.getElementById("genero");
    let tituloPresentacion = document.getElementById("tituloPresentacion");
    let estrenoPresentacion = document.getElementById("fecha-estrenoPresentacion");
    let descripcionPresentacion = document.getElementById("descripcionPresentacion");
    let generoPresentacion = document.getElementById("generoPresentacion");

    funcionQuePasaLoQueEscribimosAOtroContenedor(titulo, tituloPresentacion);
    funcionQuePasaLoQueEscribimosAOtroContenedor(estreno, estrenoPresentacion);
    funcionQuePasaLoQueEscribimosAOtroContenedor(descripcion, descripcionPresentacion);
    funcionQuePasaLoQueCambiamosEnElInputAOtroContenedor(estreno, estrenoPresentacion);
    funcionQuePasaLoQueCambiamosEnElInputAOtroContenedor(genero, generoPresentacion)
}


function funcionQuePasaLoQueEscribimosAOtroContenedor( input, contenedor ){

    //agregamos el evento keypress al contenedor input
    input.addEventListener("keyup", ( e ) => {
        //mandamos lo que se escribio al contenedor que queremos
        contenedor.innerHTML = `${ e.target.value }`
    })

}

function funcionQuePasaLoQueCambiamosEnElInputAOtroContenedor( input, contenedor ){

    //agregamos el evento keypress al contenedor input
    input.addEventListener("change", ( e ) => {
        //mandamos lo que se escribio al contenedor que queremos
        contenedor.innerHTML = `${ e.target.value }`
    })

}

let funcionParaVerLaPortadaSubidaPorElInput = () => {
    let inputFilePortada = document.getElementById("portada");
    let portada = document.getElementById("portadaPresentacion")

    inputFilePortada.addEventListener("change", ( e ) => {
        //creamos un objeto FileReader
        let reader = new FileReader();

        //leemos el archivo subido, que va a ser la imagen
        reader.readAsDataURL(e.target.files[0]);

        //cuando haya leido el archivo, modificamos el src de la imagen con el objeto reader
        reader.onload = () => {
            portada.src = reader.result;
        }
    })
}