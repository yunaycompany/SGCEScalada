Ext.define('SGCEscalada.store.ciudad.CiudadStore', {
    extend: 'Ext.data.Store',
    fields: ['idCiudad', 'idCanton','ciudad'],
    proxy: {
        type: 'ajax',
        url: 'listarCiudades.htm'
    },
    autoLoad: false
});