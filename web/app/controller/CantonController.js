Ext.define('SGCEscalada.controller.CantonController', {
    extend: 'Ext.app.Controller',

    views: [
        'canton.AdicionarCanton',
        'canton.EliminarCanton',
        'provincia.CbxProvincias',
        'canton.CbxCantones',
        'canton.MenuCanton'

    ],
    stores: ['canton.CantonesStore'],
    refs: [
        {
            ref: 'adicionarCanton',
            selector: 'adicionarCanton'
        },
        {
            ref: 'menuCanton',
            selector: 'menuCanton'
        },
        {
            ref: 'eliminarCanton',
            selector: 'eliminarCanton'
        },
        {
            ref: 'cbxCantones',
            selector: 'cbxCantones'
        }
    ],


    init: function () {
        this.control({
            'adicionarCanton button[action=aceptarAdicionarCanton]': {
                click: this.onClickAceptarAdicionarCanton
            },
            'eliminarCanton button[action=eliminarCanton]': {
                click: this.onClickEliminarCanton
            },
            'adicionarCanton button[action=cancelarAdicionarCanton]': {
                click: this.onClickCancelarAdicionarCanton
            },
            'eliminarCanton button[action=cancelarEliminarCanton]': {
                click: this.onClickCancelarAdicionarCanton
            },
            'menuCanton component[action=menuAdicionarCanton]': {
                click: this.onClickMenuAdicionarCanton
            },
            'menuCanton component[action=menuEliminarCanton]': {
                click: this.onClickMenuEliminarCanton
            }
        });
    },

    onClickEliminarCanton: function (th) {
        var me = this,
            form = th.up('form').getForm();
        form.submit({
            url: 'eliminarCanton.htm',
            method: 'POST',
            success: function (form) {
                Ext.widget('msjInformacion', {mensaje: 'Cant&oacute;n eliminado satisfactoriamente.'});
                form.reset();
                me.getCbxCantones().getStore().load();             
            },
            failure: function (form, action) {
                Ext.Msg.alert('Error. ', action.result.msg);
                form.reset();
            }
        });
    },

    onClickAceptarAdicionarCanton: function () {
        var me = this,
            form = me.getAdicionarCanton().frmAdicionarCanton.getForm();
        form.submit({
            url: 'insertarCanton.htm',
            method: 'POST',
            success: function (form) {
                Ext.widget('msjInformacion', {mensaje: 'Cant&oacute;n insertado satisfactoriamente.'});
                form.reset();
            
            },
            failure: function (form, action) {
                Ext.Msg.alert('Error. ', action.result.msg);
                form.reset();
            }
        });
    },

    onClickCancelarAdicionarCanton: function (th) {
        th.up('window').destroy();
    },

    onClickMenuAdicionarCanton: function(){
        Ext.widget('adicionarCanton');
    },

    onClickMenuEliminarCanton: function(){
        Ext.widget('eliminarCanton');
    }
});