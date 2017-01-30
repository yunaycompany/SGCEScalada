Ext.define('SGCEscalada.view.comun.MensajeInformacion', {
    alias:'widget.msjInformacion',
    constructor: function (config) {
        var format = config.mensaje || '';
        var title = config.title || '';
        var code = config.code || 1;
        this.msg(title, format, code);
    },
    createBox: function (t, s) {
        return '<div class="msg"><div id="imgOk"></div><h3>' + t + '</h3><p>' + s + '</p></div>';
    },
    msg: function (title, format) {
        var me = this;

        var msgCt;
        if (!msgCt) {
            msgCt = Ext.DomHelper.insertFirst(document.body, {id: 'msg-div'}, true);
        }
        var s = Ext.String.format.apply(String, Array.prototype.slice.call(arguments, 1));
        var m = Ext.DomHelper.append(msgCt, me.createBox(title, s), true);
        m.hide();
        m.slideIn('t').ghost("t", {delay: 3000, remove: true});
    }
});