function cargarComponente(componente) {
    console.log('Intentando cargar componente: ' + componente);

    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        console.log('Estado del XMLHttpRequest:', xhr.readyState, 'Status:', xhr.status);

        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                // Asegúrate de extraer solo el contenido del componente
                var inicio = xhr.responseText.indexOf('<ui:fragment');
                var fin = xhr.responseText.lastIndexOf('</ui:fragment>') + '</ui:fragment>'.length;
                var contenidoComponente = xhr.responseText.substring(inicio, fin);

                console.log('Contenido del componente:', contenidoComponente);

                document.getElementById('componenteContainer').innerHTML = contenidoComponente;
            } else {
                console.error('Error al cargar el componente ' + componente + '. Estado:', xhr.status);
            }
        }
    };

    xhr.onerror = function () {
        console.error('Error de red al intentar cargar el componente ' + componente);
    };

    xhr.open('GET', componente, true);
    xhr.send();
}
