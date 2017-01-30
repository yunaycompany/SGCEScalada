Ext.define('SGCEscalada.store.canton.CantonesStore', {
    extend: 'Ext.data.Store',
    fields: ['idCanton', 'canton'],
    proxy: {
        type: 'ajax',
        url: 'listarCantonesPorIdProvincia.htm'
    },
    autoLoad: false
});