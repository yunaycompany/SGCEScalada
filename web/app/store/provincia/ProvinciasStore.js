Ext.define('SGCEscalada.store.provincia.ProvinciasStore', {
    extend: 'Ext.data.Store',
    fields: ['idProvincia', 'provincia'],
    proxy: {
        type: 'ajax',
        url: 'listarProvincias.htm'
    },
    autoLoad: true
});