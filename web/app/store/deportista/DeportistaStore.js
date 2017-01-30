Ext.define('SGCEscalada.store.deportista.DeportistaStore', {
    extend: 'Ext.data.Store',
    fields: ['nombre', 'apellido1', 'apellido2', 'sexo','nacimiento','provincia','canton','parroquia'],
    autoLoad: true
});