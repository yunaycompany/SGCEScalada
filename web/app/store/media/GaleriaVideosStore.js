Ext.define('SGCEscalada.store.media.GaleriaVideosStore', {
    extend: 'Ext.data.Store',
    model: 'SGCEscalada.model.GaleriaVideosModel',
    sortInfo: {
        field: 'name',
        direction: 'ASC'
    },
    autoLoad: true,
    proxy: {
        type: 'ajax',
        url: 'listarVideos.htm'
    }
});