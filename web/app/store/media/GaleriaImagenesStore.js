Ext.define('SGCEscalada.store.media.GaleriaImagenesStore', {
    extend: 'Ext.data.Store',
    model: 'SGCEscalada.model.GaleriaImagenesModel',
    sortInfo: {
        field: 'name',
        direction: 'ASC'
    },
    autoLoad: true,
    proxy: {
        type: 'ajax',
        url: 'listarImagenes.htm'
    }
});