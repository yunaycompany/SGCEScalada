Ext.define('SGCEscalada.controller.DireccionController', {
    extend: 'Ext.app.Controller',

    views: [
        'provincia.CbxProvincias',
        'canton.CbxCantones',
        'parroquia.CbxParroquias',
        'direccion.FsDireccion',
        'deportista.AdicionarDeportista',
        'lugar.CbxLugares',
         'ciudad.CbxCiudad'
        
    ],

    refs: [
        {
            ref: 'fsDireccion',
            selector: 'fsDireccion'
        },
        {
            ref: 'adicionarDeportista',
            selector: 'adicionarDeportista'
        },
        {
            ref: 'cbxLugares',
            selector: 'cbxLugares'
        }
    ],
    stores: [
        'provincia.ProvinciasStore',
        'canton.CantonesStore',
        'parroquia.ParroquiasStore',
        'ciudad.CiudadStore',
        'lugar.LugaresStore'
    ],

    init: function () {
        this.control({
            'fsDireccion combo[action=direccionProvincia]': {
                select: this.onSelectDireccionProvincia
            },
            'fsDireccion combo[action=direccionCanton]': {
                select: this.onSelectDireccionCanton
            },
             'fsDireccion combo[action=direccionCiudad]': {
                select: this.onSelectDireccionCiudad
            },
            'fsDireccion combo[action=direccionParroquia]': {
                select: this.onSelectDireccionParroquia
            }
        });
    },

    onSelectDireccionProvincia: function (th, records) {
        var me = this,
            cbxCanton = me.getFsDireccion().cbxCantonDireccion,
            cbxCiudad=me.getFsDireccion().cbxCiudadDireccion,
            cbxParroquia = me.getFsDireccion().cbxParroquiaDireccion,
            wndAdicionarDeportista = me.getAdicionarDeportista()? me.getAdicionarDeportista():false,
            cbxFederacion = wndAdicionarDeportista? me.getAdicionarDeportista().cbxFederacionDeportista:false/*,
            wndAdicionarCompetencia = me.getAdicionarCompetencia() ? me.getAdicionarCompetencia() : false,
            cbxLugar = wndAdicionarCompetencia ? me.getAdicionarCompetencia().cbxLugarDireccion : false*/;

        cbxCanton.getStore().on('beforeload', function (action, option) {
            option.params = {};
            option.params.idProvincia = records[0].data.idProvincia;
        });
        cbxParroquia.reset();
        cbxParroquia.setDisabled(true);
         cbxCiudad.reset();
        cbxCiudad.setDisabled(true);
        cbxCanton.reset();
        cbxCanton.getStore().load();
        cbxCanton.setDisabled(false);

       if(cbxFederacion){
           cbxFederacion.getStore().on('beforeload', function (action, option) {
               option.params = {};
               option.params.idProvincia = records[0].data.idProvincia;
           });
           cbxFederacion.reset();
           cbxFederacion.getStore().load();
           wndAdicionarDeportista.fsFederacionDeportista.setDisabled(false);
       }

       if(me.getCbxLugares()){
           me.getCbxLugares().reset();
           me.getCbxLugares().setDisabled(true);
       }
    },

    onSelectDireccionCanton: function (th, records) {
        var me = this,
            cbxCiudad = me.getFsDireccion().cbxCiudadDireccion;
        cbxCiudad.getStore().on('beforeload', function (action, option) {
            option.params = {};
            option.params.idCanton = records[0].data.idCanton;
        });
        cbxCiudad.reset();
        cbxCiudad.getStore().load();
        cbxCiudad.setDisabled(false);

        if (me.getCbxLugares()) {
            me.getCbxLugares().reset();
            me.getCbxLugares().setDisabled(true);
        }
    },

    onSelectDireccionCiudad: function(th, records){
        var me = this,
            cbxParroquia = me.getFsDireccion().cbxParroquiaDireccion;
        cbxParroquia.getStore().on('beforeload', function (action, option) {
            option.params = {};
            option.params.idCiudad= records[0].data.idCiudad;
        });
        cbxParroquia.reset();
        cbxParroquia.getStore().load();
        cbxParroquia.setDisabled(false);

        if (me.getCbxLugares()) {
            me.getCbxLugares().reset();
            me.getCbxLugares().setDisabled(true);
        }
    }, onSelectDireccionParroquia: function(th, records){
        var me = this;
        if (me.getCbxLugares()) {
            me.getCbxLugares().getStore().on('beforeload', function (action, option) {
                option.params = {};
                option.params.idParroquia = records[0].data.idParroquia;
            });
            me.getCbxLugares().reset();
            me.getCbxLugares().getStore().load();
            me.getCbxLugares().setDisabled(false);
        }
    }
});