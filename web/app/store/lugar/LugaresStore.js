Ext.define('SGCEscalada.store.lugar.LugaresStore', {
    extend: 'Ext.data.Store',
    fields: ['idLugar', 'lugar'],
    proxy: {
        type: 'ajax',
        url: 'listarLugaresPorIdParroquia.htm'
    },
    autoLoad: false
});