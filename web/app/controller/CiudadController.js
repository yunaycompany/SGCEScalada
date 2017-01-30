Ext.define('SGCEscalada.controller.CiudadController', {
    extend: 'Ext.app.Controller',

    views: [
        'ciudad.AdicionarCiudad',
        'ciudad.EliminarCiudad',
        'ciudad.MenuCiudad',
        'ciudad.CbxCiudad'
    ],

    refs: [
        {
            ref: 'adicionarCiudad',
            selector: 'adicionarCiudad'
        },
        {
            ref: 'menuCiudad',
            selector: 'menuCiudad'
        },
        {
            ref: 'eliminarCiudad',
            selector: 'eliminarCiudad'
        },
        {
            ref: 'cbxCiudad',
            selector: 'cbxCiudad'
        }
    ],
    stores: ['canton.CantonesStore', 'provincia.ProvinciasStore'],

    init: function () {
        this.control({
            'adicionarCiudad combo[action=adicionarCiudadProvincia]': {
                select: this.onSelectProvinciaAdicionarCiudad
            },
            'adicionarCiudad combo[action=adicionarCiudadCanton]': {
                beforeload: this.onBeforeLoadCantonAdicionarCiudad
            },
            'adicionarCiudad button[action=aceptarAdicionarCiudad]': {
                click: this.onClickAceptarAdicionarCiudad
            },
            'eliminarCiudad button[action=eliminarCiudad]': {
                click: this.onClickEliminarCiudad
            },
            'adicionarCiudad button[action=cancelarAdicionarCiudad]': {
                click: this.onClickCancelarAdicionarCiudad
            },
            'eliminarCiudad button[action=cancelarEliminarCiudad]': {
                click: this.onClickCancelarAdicionarCiudad
            },
            'menuCiudad component[action=menuAdicionarCiudad]': {
                click: this.onClickMenuAdicionarCiudad
            },
            'menuCiudad component[action=menuEliminarCiudad]': {
                click: this.onClickMenuEliminarCiudad
            }
        });
    },

    onSelectProvinciaAdicionarCiudad: function(th,records){
        var me = this,
            cbxCanton = me.getAdicionarCiudad().cbxCantonAdicionarCiudad;
        cbxCanton.getStore().on('beforeload',function(action,option){
            option.params = {};
            option.params.idProvincia = records[0].data.idProvincia;
        });
        cbxCanton.reset();
        cbxCanton.getStore().load();
        cbxCanton.setDisabled(false);
    },

    onBeforeLoadCantonAdicionarCiudad: function(th){

    },

    onClickAceptarAdicionarCiudad: function () {
        var me = this,
            form = me.getAdicionarCiudad().frmAdicionarCiudad.getForm();
        form.submit({
            url: 'insertarCiudad.htm',
            method: 'POST',
            success: function (form) {
                Ext.widget('msjInformacion', {mensaje: 'Ciudad insertada satisfactoriamente.'});
                form.reset();
              
            },
            failure: function (form, action) {
                Ext.Msg.alert('Error. ', action.result.msg);
                form.reset();
            }
        });
    },

    onClickEliminarCiudad: function (th) {
        var me = this,
            form = th.up('form').getForm();
        form.submit({
            url: 'eliminarCiudad.htm',
            method: 'POST',
            success: function (form) {
                Ext.widget('msjInformacion', {mensaje: 'Ciudad eliminada satisfactoriamente.'});
                form.reset();
                me.getCbxCiudad().getStore().load();
              
            },
            failure: function (form, action) {
                Ext.Msg.alert('Error. ', action.result.msg);
                form.reset();
            }
        });
    },

    onClickCancelarAdicionarCiudad: function (th) {
        th.up('window').destroy();
    },

    onClickMenuAdicionarCiudad: function () {
       Ext.widget('adicionarCiudad');
    },

    onClickMenuEliminarCiudad: function () {
       Ext.widget('eliminarCiudad');
    }
});