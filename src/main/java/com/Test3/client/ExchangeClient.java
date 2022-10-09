package com.Test3.client;

import com.Test3.model.ConvertModel;
import com.Test3.model.Query;

public interface ExchangeClient {

    ConvertModel exchange(Query query);

}
