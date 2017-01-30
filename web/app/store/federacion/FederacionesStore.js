Ext.define('SGCEscalada.store.federacion.FederacionesStore', {
    extend: 'Ext.data.Store',
    model:'SGCEscalada.model.FederacionModel',
    proxy: {
        type: 'ajax',
        url: 'listarFederaciones.htm'
    },
    autoLoad: false
});