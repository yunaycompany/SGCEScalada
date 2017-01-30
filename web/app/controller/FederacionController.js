Ext.define('SGCEscalada.controller.FederacionController', {
    extend: 'Ext.app.Controller',

    views: [
        'federacion.AdicionarFederacion',
        'federacion.EliminarFederacion',
        'provincia.CbxProvincias',
        'federacion.MenuFederacion'
    ],

    stores: ['provincia.ProvinciasStore','federacion.FederacionesStore'],
    refs: [
        {
            ref: 'adicionarFederacion',
            selector: 'adicionarFederacion'
        },
        {
            ref: 'eliminarFederacion',
            selector: 'eliminarFederacion'
        },
        {
            ref: 'menuFederacion',
            selector: 'menuFederacion'
        },
        {
            ref: 'cbxFederaciones',
            selector: 'cbxFederaciones'
        }
    ],

    init: function () {
        this.control({
            'adicionarFederacion button[action=aceptarAdicionarFederacion]': {
                click: this.onClickAceptarAdicionarFederacion
            },
            'eliminarFederacion button[action=eliminarFederacion]': {
                click: this.onClickEliminarFederacion
            },
            'eliminarFederacion button[action=cancelarEliminarFederacion]': {
                click: this.onClickCancelarAdicionarFederacion
            },
            'adicionarFederacion button[action=cancelarAdicionarFederacion]': {
                click: this.onClickCancelarAdicionarFederacion
            },
            'menuFederacion component[action=menuAdicionarFederacion]': {
                click: this.onClickMenuAdicionarFederacion
            },
            'menuFederacion component[action=menuEliminarFederacion]': {
                click: this.onClickMenuEliminarFederacion
            }
        });
    },

    onClickEliminarFederacion: function (th) {
        var me = this,
            form = th.up('form').getForm();
        form.submit({
            url: 'eliminarFederacion.htm',
            method: 'POST',
            success: function (form) {
                Ext.widget('msjInformacion',{mensaje: 'Federaci&oacute;n eliminada satisfactoriamente.'});
                form.reset();
                me.getCbxFederaciones().getStore().load();
            },
            failure: function (form, action) {
                Ext.Msg.alert('Error. ', action.result.msg);
                form.reset();
            }
        });
    },

    onClickAceptarAdicionarFederacion: function () {
        var me = this,
            form = me.getAdicionarFederacion().frmAdicionarFederacion.getForm();
        form.submit({
            url: 'insertarFederacionProvincial.htm',
            method: 'POST',
            success: function (form) {
                Ext.widget('msjInformacion',{mensaje: 'Federaci&oacute;n insertada satisfactoriamente.'});
                form.reset();
            },
            failure: function (form, action) {
                Ext.Msg.alert('Error. ', action.result.msg);
                form.reset();
            }
        });
    },

    onClickCancelarAdicionarFederacion: function (th) {
        th.up('window').destroy();
    },

    onClickMenuAdicionarFederacion: function () {
        Ext.widget('adicionarFederacion');
    },

    onClickMenuEliminarFederacion: function () {
        Ext.widget('eliminarFederacion');
    }
});