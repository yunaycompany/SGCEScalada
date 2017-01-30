Ext.define('SGCEscalada.store.parroquia.ParroquiasStore', {
    extend: 'Ext.data.Store',
    fields: ['idParroquia', 'idCiudad','parroquia'],
    proxy: {
        type: 'ajax',
        url: 'listarParroquias.htm'
    },
    autoLoad: false
});