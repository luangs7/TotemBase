package br.com.luan.totenbase.extras.realm;


import br.com.luan.totenbase.extras.model.Questions;
import br.com.luan.totenbase.extras.model.Search;
import br.com.luan.totenbase.extras.model.User;
import io.realm.annotations.RealmModule;

/**
 * Created by luan on 24/11/2016.
 */

@RealmModule(classes = {Search.class, User.class, Questions.class})
public class ObjectModule {
}
