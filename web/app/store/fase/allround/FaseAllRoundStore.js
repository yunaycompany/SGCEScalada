Ext.define('SGCEscalada.store.fase.allround.FaseAllRoundStore', {
    extend: 'Ext.data.Store',
    model:'SGCEscalada.model.FaseAllRoundModel',
    proxy: {
        type: 'ajax',
        url: 'listarAllRound.htm'
    },
    sorters: [{
        property: 'totalPuntos',
        direction: 'DESC'
    }],
    autoLoad: false
});