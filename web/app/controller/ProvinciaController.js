Ext.define('SGCEscalada.controller.ProvinciaController', {
    extend: 'Ext.app.Controller',

    views: [
        'provincia.AdicionarProvincia',
        'provincia.EliminarProvincia',
        'provincia.CbxProvincias',
        'provincia.MenuProvincia'

    ],
    stores: ['provincia.ProvinciasStore'],

    refs: [
        {
            ref: 'adicionarProvincia',
            selector: 'adicionarProvincia'
        },
        {
            ref: 'eliminarProvincia',
            selector: 'eliminarProvincia'
        },
        {
            ref: 'menuProvincia',
            selector: 'menuProvincia'
        },
        {
            ref: 'cbxProvincias',
            selector: 'cbxProvincias'
        }
    ],


    init: function () {
        this.control({
            'adicionarProvincia button[action=aceptarAdicionarProvincia]': {
                click: this.onClickAceptarAdicionarProvincia
            },
            'eliminarProvincia button[action=eliminarProvincia]': {
                click: this.onClickEliminarProvincia
            },
            'adicionarProvincia button[action=cancelarAdicionarProvincia]': {
                click: this.onClickCancelarAdicionarProvincia
            },
            'eliminarProvincia button[action=cancelarEliminarProvincia]': {
                click: this.onClickCancelarAdicionarProvincia
            },
            'menuProvincia component[action=menuAdicionarProvincia]': {
                click: this.onClickMenuAdicionarProvincia
            },
            'menuProvincia component[action=menuEliminarProvincia]': {
                click: this.onClickMenuEliminarProvincia
            }
        });
    },

    onClickEliminarProvincia: function (th) {
        var me = this,
            form = th.up('form').getForm();
        form.submit({
            url:'eliminarProvincia.htm',
            method: 'POST',
            success: function (form) {
                Ext.widget('msjInformacion', {mensaje: 'Provincia eliminada satisfactoriamente.'});
                form.reset();
                me.getCbxProvincias().getStore().load();
                
            },
            failure: function (form, action) {
                Ext.Msg.alert('Error. ', action.result.msg);
                form.reset();
            }
        });
    },

    onClickAceptarAdicionarProvincia: function () {
        var me = this,
            form = me.getAdicionarProvincia().frmAdicionarProvincia.getForm();
        form.submit({
            url:'insertarProvincia.htm',
            method: 'POST',
            success: function (form) {
                Ext.widget('msjInformacion', {mensaje: 'Provincia insertada satisfactoriamente.'});
                form.reset();
               
            },
            failure: function (form, action) {
                Ext.Msg.alert('Error. ', action.result.msg);
                form.reset();
            }
        });
    },

    onClickCancelarAdicionarProvincia: function (th) {
        th.up('window').destroy();
    },

    onClickMenuAdicionarProvincia: function () {
        Ext.widget('adicionarProvincia');
    },

    onClickMenuEliminarProvincia: function () {
        Ext.widget('eliminarProvincia');
    }
});