Ext.define('SGCEscalada.store.fase.velocidad.FaseVelocidadStore', {
    extend: 'Ext.data.Store',
    model:'SGCEscalada.model.FaseVelocidadModel',
    proxy: {
        type: 'ajax',
        url: 'listarFaseVelocidad.htm'
    },
    sorters: this.final||this.general? ['tiempoFinal']:['mejorTiempo','tiempo1','tiempo2'],
    autoLoad: false
});