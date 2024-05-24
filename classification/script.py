import os.path

test_dir = "test"
train_dir = "train"
balance_dir = "баланс"
operation_dir = "операции"

if not os.path.exists(os.path.join(test_dir)):
    os.makedirs(os.path.join(test_dir))

def init_dir(root_dir: str, dir_name: str):
    if not os.path.exists(os.path.join(root_dir, dir_name)):
        os.makedirs(os.path.join(root_dir, dir_name))


def write_dataset(root_dir: str, dir_name: str, requests: list[str]):
    for i, req in enumerate(requests):
        filename = os.path.join(root_dir, dir_name, f"{i}.txt")

        with open(filename, "w", encoding="utf-8") as file:
            file.write(req)


init_dir(train_dir, operation_dir)
init_dir(train_dir, balance_dir)
init_dir(test_dir, operation_dir)
init_dir(test_dir, balance_dir)


operations_requests = [
    "Проверьте последние транзакции на моем счету.",
    "Покажите мне историю переводов за последний месяц.",
    "Какие покупки я сделал за последние три дня?",
    "Сколько я потратил на рестораны за прошлую неделю?",
    "Есть ли у меня неподтвержденные транзакции?",
    "Могу ли я получить выписку по счету за январь?",
    "Отправьте список всех платежей, сделанных с моего счета в этом году.",
    "Какие платежи были произведены вчера?",
    "Покажите все мои переводы за последнюю неделю.",
    "Есть ли у меня повторяющиеся платежи?",
    "Я хочу узнать детали моей последней транзакции.",
    "Могу ли я увидеть все входящие платежи за текущий месяц?",
    "Отправьте мне отчёт о всех снятиях наличных за последние две недели.",
    "Какую сумму я перевел на счет 123456789 за последний месяц?",
    "Проверьте статус моего последнего перевода.",
    "Покажите мне все покупки, сделанные с моей карты за текущий месяц.",
    "Сколько денег было снято с моего счета за последние три месяца?",
    "Есть ли у меня просроченные платежи?",
    "Могу ли я узнать сумму всех комиссий за последний год?",
    "Покажите мне все мои автоматические платежи.",
    "Отправьте список всех входящих переводов за последний месяц.",
    "Какие были мои последние пять транзакций?",
    "Сколько денег я потратил на коммунальные услуги за прошлый месяц?",
    "Есть ли у меня несанкционированные платежи?",
    "Могу ли я получить детализацию всех покупок за прошлую неделю?",
    "Проверьте, были ли возвраты средств на мой счет.",
    "Покажите историю всех переводов на мой счет за последние три месяца.",
    "Сколько денег было переведено на мой счет за последний год?",
    "Какие были мои последние транзакции с использованием мобильного приложения?",
    "Могу ли я узнать сумму всех транзакций по категории 'продукты' за текущий месяц?",
    "Проверьте, есть ли у меня незавершенные платежи.",
    "Покажите мне все мои траты на развлечения за последний месяц.",
    "Сколько денег было переведено с моего счета на благотворительность?",
    "Есть ли у меня активные подписки?",
    "Могу ли я получить отчёт по всем моим банковским счетам за текущий год?",
    "Покажите историю всех покупок, сделанных в интернете.",
    "Какие были мои последние транзакции по кредитной карте?",
    "Сколько я потратил на путешествия за последние полгода?",
    "Есть ли у меня необработанные переводы?",
    "Могу ли я узнать детали всех транзакций с определенным магазином?",
    "Проверьте статус всех моих переводов за последний месяц.",
    "Покажите мне историю всех входящих платежей за прошлый год.",
    "Сколько денег было списано на оплату налогов?",
    "Есть ли у меня просроченные переводы?",
    "Могу ли я узнать сумму всех покупок, сделанных за последние шесть месяцев?",
    "Проверьте, были ли возвраты на мой счет за последний месяц.",
    "Покажите историю всех моих переводов за последние два года.",
    "Сколько денег было потрачено на транспорт за текущий месяц?",
    "Какие были мои последние транзакции по дебетовой карте?",
    "Есть ли у меня активные платежные поручения?",
    "Могу ли я получить детализацию всех транзакций по категориям?",
    "Покажите мне все мои траты на здоровье и спорт.",
    "Сколько я потратил на одежду за последний месяц?",
    "Есть ли у меня незавершенные переводы?",
    "Покажите мне историю всех моих платежей за последние три года.",
    "Сколько денег было списано на оплату услуг связи?",
    "Какие были мои последние транзакции по PayPal?",
    "Есть ли у меня активные заказы на покупку валюты?",
    "Могу ли я узнать сумму всех переводов на определенный счет?",
    "Проверьте статус всех моих платежей за последний месяц.",
    "Покажите мне историю всех транзакций по кредитным картам.",
    "Сколько денег было потрачено на учебу за текущий год?",
    "Какие были мои последние транзакции по интернет-банкингу?",
    "Есть ли у меня активные депозитные счета?",
    "Могу ли я получить отчёт о всех моих расходах за последний месяц?",
    "Покажите мне историю всех моих платежей за последние полгода.",
    "Сколько денег было переведено на благотворительные организации?",
    "Какие были мои последние транзакции с использованием NFC?",
    "Есть ли у меня активные запросы на перевод?",
    "Могу ли я узнать сумму всех транзакций по категориям за текущий месяц?",
    "Проверьте, были ли возвраты средств за прошлый год.",
    "Покажите мне историю всех покупок за последние три месяца.",
    "Сколько денег было потрачено на аренду жилья за текущий месяц?",
    "Какие были мои последние транзакции по мобильному банкингу?",
    "Есть ли у меня незавершенные платежи за прошлую неделю?",
    "Могу ли я получить детализацию всех покупок за последний год?",
    "Покажите мне историю всех моих переводов за текущий месяц.",
    "Сколько денег было переведено с моего счета за последние три месяца?",
    "Какие были мои последние транзакции с использованием QR-кодов?",
    "Есть ли у меня активные инвестиционные счета?",
    "Могу ли я узнать сумму всех переводов на мой счет за последний год?",
    "Проверьте статус всех моих транзакций за прошлый месяц.",
    "Покажите мне историю всех покупок, сделанных в магазинах.",
    "Сколько денег было потрачено на развлечения за последний год?",
    "Какие были мои последние транзакции по виртуальной карте?",
    "Есть ли у меня активные запросы на возврат средств?",
    "Могу ли я получить отчёт о всех моих доходах за текущий месяц?",
    "Покажите мне историю всех моих платежей за последние два года.",
    "Сколько денег было переведено на счета других банков?",
    "Какие были мои последние транзакции с использованием банковского приложения?",
    "Есть ли у меня незавершенные переводы за текущий месяц?",
    "Могу ли я получить детализацию всех транзакций по категориям?",
    "Покажите мне историю всех покупок за последний год.",
    "Сколько денег было переведено с моего счета за последние полгода?",
    "Какие были мои последние транзакции по мобильному приложению?",
    "Есть ли у меня активные подписки за текущий месяц?",
    "Могу ли я узнать сумму всех моих расходов за последний квартал?",
    "Какие были мои последние транзакции с использованием банковской карты?",
    "Сколько я потратил на развлечения за текущий месяц?",
    "Есть ли у меня незавершенные операции за последний месяц?",
    "Покажите мне историю всех переводов за последние полгода.",
    "Сколько денег было потрачено на продукты за текущий месяц?",
    "Какие были мои последние транзакции с использованием интернет-банка?",
    "Есть ли у меня активные ссуды?",
    "Могу ли я получить отчёт о всех моих доходах за текущий год?",
    "Покажите мне историю всех моих транзакций за последние три года."
]

balance_requests = [
    "Какой у меня баланс на текущем счете?",
    "Покажите остаток на моем основном счету.",
    "Сколько денег у меня на карточке?",
    "Какая сумма на моем депозите?",
    "Узнайте баланс моего сберегательного счета.",
    "Проверьте остаток на моем кредитном счету.",
    "Каков текущий баланс на моем банковском счете?",
    "Сколько средств у меня на счету?",
    "Какой остаток на моем счете на сегодняшний день?",
    "Какая сумма доступна на моем расчетном счету?",
    "Покажите текущий баланс моего счета.",
    "Сколько денег осталось на моем дебетовом счету?",
    "Какую сумму я могу использовать со своего счета?",
    "Проверьте остаток средств на моем валютном счету.",
    "Какой баланс на моем накопительном счету?",
    "Какая сумма на моем текущем счете?",
    "Сколько денег на моем основном счете?",
    "Какой баланс у меня на карте?",
    "Покажите остаток средств на моем депозитном счете.",
    "Узнайте текущий баланс моего сберегательного счета.",
    "Проверьте сумму на моем кредитном счете.",
    "Каков баланс на моем банковском счете?",
    "Сколько денег на моем счету?",
    "Какой текущий остаток на моем счете?",
    "Какая сумма доступна на моем основном счете?",
    "Покажите баланс моего счета.",
    "Сколько денег осталось на моем расчетном счете?",
    "Какую сумму я могу снять со своего счета?",
    "Проверьте баланс на моем валютном счете.",
    "Каков остаток на моем накопительном счете?",
    "Сколько у меня денег на текущем счете?",
    "Какой остаток на моем основном счете?",
    "Сколько средств на моей карте?",
    "Какая сумма на моем депозитном счете?",
    "Проверьте баланс моего сберегательного счета.",
    "Каков остаток на моем кредитном счете?",
    "Сколько денег на моем банковском счете?",
    "Какой баланс на моем счету?",
    "Покажите текущий остаток на моем счете.",
    "Какая сумма доступна на моем текущем счете?",
    "Покажите баланс моего расчетного счета.",
    "Сколько денег осталось на моем основном счете?",
    "Какую сумму я могу использовать со своего расчетного счета?",
    "Проверьте остаток средств на моем накопительном счете.",
    "Каков баланс на моем валютном счете?",
    "Сколько денег на моем текущем счете?",
    "Какой остаток на моем основном счете?",
    "Сколько средств на моей дебетовой карте?",
    "Какая сумма на моем накопительном счете?",
    "Проверьте баланс моего расчетного счета.",
    "Каков остаток на моем валютном счете?",
    "Сколько денег на моем сберегательном счете?",
    "Какой баланс на моем депозитном счете?",
    "Покажите текущий остаток на моем основном счете.",
    "Какая сумма доступна на моем валютном счете?",
    "Покажите баланс моего кредитного счета.",
    "Сколько денег осталось на моем депозитном счете?",
    "Какую сумму я могу снять с моего счета?",
    "Проверьте баланс на моем сберегательном счете.",
    "Каков остаток на моем расчетном счете?",
    "Сколько денег на моем валютном счете?",
    "Какой баланс на моем текущем счете?",
    "Покажите текущий остаток на моем расчетном счете.",
    "Какая сумма доступна на моем кредитном счете?",
    "Покажите баланс моего накопительного счета.",
    "Сколько денег осталось на моем текущем счете?",
    "Какую сумму я могу использовать со своего депозитного счета?",
    "Проверьте остаток средств на моем основном счете.",
    "Каков баланс на моем расчетном счете?",
    "Сколько денег на моем депозитном счете?",
    "Какой баланс на моем валютном счете?",
    "Покажите текущий остаток на моем сберегательном счете.",
    "Какая сумма доступна на моем накопительном счете?",
    "Покажите баланс моего расчетного счета.",
    "Сколько денег осталось на моем текущем счете?",
    "Какую сумму я могу использовать со своего кредитного счета?",
    "Проверьте баланс на моем депозитном счете.",
    "Каков остаток на моем накопительном счете?",
    "Сколько денег на моем текущем счете?",
    "Какой остаток на моем основном счете?",
    "Сколько средств на моей дебетовой карте?",
    "Какая сумма на моем накопительном счете?",
    "Проверьте баланс моего расчетного счета.",
    "Каков остаток на моем валютном счете?",
    "Сколько денег на моем сберегательном счете?",
    "Какой баланс на моем депозитном счете?",
    "Покажите текущий остаток на моем основном счете.",
    "Какая сумма доступна на моем валютном счете?",
    "Покажите баланс моего кредитного счета.",
    "Сколько денег осталось на моем депозитном счете?",
    "Какую сумму я могу снять с моего счета?",
    "Проверьте баланс на моем сберегательном счете.",
    "Каков остаток на моем расчетном счете?",
    "Сколько денег на моем валютном счете?",
    "Какой баланс на моем текущем счете?",
    "Покажите текущий остаток на моем расчетном счете.",
    "Какая сумма доступна на моем кредитном счете?",
    "Покажите баланс моего накопительного счета.",
    "Сколько денег осталось на моем текущем счете?",
    "Какую сумму я могу использовать со своего депозитного счета?",
    "Сколько денег на моем счете сейчас?",
    "Покажите остаток по всем моим счетам.",
    "Какой текущий баланс по кредитной карте?",
    "Проверьте баланс на всех моих счетах.",
    "Сколько я могу снять с моего текущего счета?",
    "Каков баланс на моем бизнес-счете?",
    "Сколько денег на моем студенческом счете?",
    "Покажите остаток по моей кредитной карте.",
    "Какая сумма на моем счете в иностранной валюте?",
    "Узнайте баланс моего счета в долларах.",
    "Какой баланс по кредитной карте на сегодняшний день?",
    "Сколько средств доступно на моем валютном счете?",
    "Покажите остаток на моем сберегательном счете на сегодня.",
    "Сколько денег на всех моих счетах?",
    "Какую сумму я могу перевести с моего текущего счета?",
    "Проверьте текущий баланс на моем расчетном счете.",
    "Каков остаток на моем депозитном счете на сегодняшний день?",
    "Сколько я могу использовать с моего кредитного счета?",
    "Покажите баланс на моем счете в рублях."
]



test_balance_dataset = [
    "Какой баланс на моем накопительном счету?",
    "Сколько денег у меня на карте?",
    "Покажите текущий баланс на моем кредитном счете.",
    "Каков остаток на моем расчетном счете?",
    "Проверьте баланс моего валютного счета.",
    "Какая сумма доступна на моем основном счету?",
    "Сколько средств у меня на депозите?",
    "Покажите остаток на моем дебетовом счете.",
    "Сколько денег осталось на моем накопительном счете?",
    "Какую сумму я могу снять с моего счета?",
    "Каков баланс на моем банковском счете?",
    "Сколько денег у меня на сберегательном счете?",
    "Покажите остаток на моем валютном счете.",
    "Какая сумма доступна на моем накопительном счете?",
    "Сколько денег на моем расчетном счете?",
    "Покажите текущий баланс на моем сберегательном счете.",
    "Каков остаток на моей карте?",
    "Сколько средств я могу использовать со своего кредитного счета?",
    "Какая сумма на моем валютном счете?",
    "Проверьте остаток на моем депозитном счете.",
    "Сколько денег у меня на счете сейчас?",
    "Покажите баланс всех моих счетов.",
    "Какой остаток на моем основном счете?",
    "Сколько средств на моей дебетовой карте?",
    "Какая сумма на моем накопительном счете?",
    "Проверьте баланс моего расчетного счета.",
    "Каков остаток на моем валютном счете?",
    "Сколько денег на моем сберегательном счете?",
    "Какой баланс на моем депозитном счете?",
    "Покажите текущий остаток на моем основном счете.",
    "Какая сумма доступна на моем валютном счете?",
    "Покажите баланс моего кредитного счета.",
    "Сколько денег осталось на моем депозитном счете?",
    "Какую сумму я могу снять с моего счета?",
    "Проверьте баланс на моем сберегательном счете.",
    "Каков остаток на моем расчетном счете?",
    "Сколько денег на моем валютном счете?",
    "Какой баланс на моем текущем счете?",
    "Покажите текущий остаток на моем расчетном счете.",
    "Какая сумма доступна на моем кредитном счете?",
    "Покажите баланс моего накопительного счета.",
    "Сколько денег осталось на моем текущем счете?",
    "Какую сумму я могу использовать со своего депозитного счета?",
    "Сколько денег на моем счете сейчас?",
    "Покажите остаток по всем моим счетам.",
    "Какой текущий баланс по кредитной карте?",
    "Проверьте баланс на всех моих счетах.",
    "Сколько я могу снять с моего текущего счета?",
    "Каков баланс на моем бизнес-счете?",
    "Сколько денег на моем студенческом счете?",
    "Покажите остаток по моей кредитной карте.",
    "Какая сумма на моем счете в иностранной валюте?",
    "Узнайте баланс моего счета в долларах.",
    "Какой баланс по кредитной карте на сегодняшний день?",
    "Сколько средств доступно на моем валютном счете?",
    "Покажите остаток на моем сберегательном счете на сегодня.",
    "Сколько денег на всех моих счетах?",
    "Какую сумму я могу перевести с моего текущего счета?",
    "Проверьте текущий баланс на моем расчетном счете.",
    "Каков остаток на моем депозитном счете на сегодняшний день?",
    "Сколько я могу использовать с моего кредитного счета?",
    "Покажите баланс на моем счете в рублях.",
    "Какой баланс у меня на карте?",
    "Сколько денег на моем основном счете?",
    "Покажите остаток средств на моем депозитном счете.",
    "Узнайте текущий баланс моего сберегательного счета.",
    "Проверьте сумму на моем кредитном счете.",
    "Каков баланс на моем банковском счете?",
    "Сколько денег на моем счету?",
    "Какой текущий остаток на моем счете?",
    "Какая сумма доступна на моем основном счете?",
    "Покажите баланс моего счета.",
    "Сколько денег осталось на моем расчетном счете?",
    "Какую сумму я могу снять со своего счета?",
    "Проверьте баланс на моем валютном счете.",
    "Каков баланс на моем накопительном счете?",
    "Сколько у меня денег на текущем счете?",
    "Какой остаток на моем основном счете?",
    "Сколько средств на моей карте?",
    "Какая сумма на моем депозитном счете?",
    "Проверьте баланс моего сберегательного счета.",
    "Каков остаток на моем кредитном счете?",
    "Сколько денег на моем банковском счете?",
    "Какой баланс на моем счету?",
    "Покажите текущий остаток на моем счете.",
    "Какая сумма доступна на моем текущем счете?",
    "Покажите баланс моего расчетного счета.",
    "Сколько денег осталось на моем основном счете?",
    "Какую сумму я могу использовать со своего расчетного счета?",
    "Проверьте остаток средств на моем накопительном счете.",
    "Каков баланс на моем валютном счете?",
    "Сколько денег на моем текущем счете?",
    "Какой остаток на моем основном счете?",
    "Сколько средств на моей дебетовой карте?",
    "Какая сумма на моем накопительном счете?",
    "Проверьте баланс моего расчетного счета.",
    "Каков остаток на моем валютном счете?",
    "Сколько денег на моем сберегательном счете?",
    "Какой баланс на моем депозитном счете?",
    "Покажите текущий остаток на моем основном счете.",
    "Какая сумма доступна на моем валютном счете?",
    "Покажите баланс моего кредитного счета.",
    "Сколько денег осталось на моем депозитном счете?",
    "Какую сумму я могу снять с моего счета?",
    "Проверьте баланс на моем сберегательном счете.",
    "Каков остаток на моем расчетном счете?",
    "Сколько денег на моем валютном счете?",
    "Какой баланс на моем текущем счете?",
    "Покажите текущий остаток на моем расчетном счете.",
    "Какая сумма доступна на моем кредитном счете?",
    "Покажите баланс моего накопительного счета.",
    "Сколько денег осталось на моем текущем счете?",
    "Какую сумму я могу использовать со своего депозитного счета?",
    "Сколько денег на моем счете сейчас?",
    "Покажите остаток по всем моим счетам.",
    "Какой текущий баланс по кредитной карте?",
    "Проверьте баланс на всех моих счетах.",
    "Сколько я могу снять с моего текущего счета?",
    "Каков баланс на моем бизнес-счете?",
    "Сколько денег на моем студенческом счете?",
    "Покажите остаток по моей кредитной карте.",
    "Какая сумма на моем счете в иностранной валюте?",
    "Узнайте баланс моего счета в долларах.",
    "Какой баланс по кредитной карте на сегодняшний день?",
    "Сколько средств доступно на моем валютном счете?",
    "Покажите остаток на моем сберегательном счете на сегодня.",
    "Сколько денег на всех моих счетах?",
    "Какую сумму я могу перевести с моего текущего счета?",
    "Проверьте текущий баланс на моем расчетном счете.",
    "Каков остаток на моем депозитном счете на сегодняшний день?",
    "Сколько я могу использовать с моего кредитного счета?",
    "Покажите баланс на моем счете в рублях.",


]

test_operations_dataset = [
    "Проверьте последние транзакции на моем счету.",
    "Покажите мне историю переводов за последний месяц.",
    "Какие покупки я сделал за последние три дня?",
    "Сколько я потратил на рестораны за прошлую неделю?",
    "Есть ли у меня неподтвержденные транзакции?",
    "Могу ли я получить выписку по счету за январь?",
    "Отправьте список всех платежей, сделанных с моего счета в этом году.",
    "Какие платежи были произведены вчера?",
    "Покажите все мои переводы за последнюю неделю.",
    "Есть ли у меня повторяющиеся платежи?",
    "Я хочу узнать детали моей последней транзакции.",
    "Могу ли я увидеть все входящие платежи за текущий месяц?",
    "Отправьте мне отчёт о всех снятиях наличных за последние две недели.",
    "Какую сумму я перевел на счет 123456789 за последний месяц?",
    "Проверьте статус моего последнего перевода.",
    "Покажите мне все покупки, сделанные с моей карты за текущий месяц.",
    "Сколько денег было снято с моего счета за последние три месяца?",
    "Есть ли у меня просроченные платежи?",
    "Могу ли я узнать сумму всех комиссий за последний год?",
    "Покажите мне все мои автоматические платежи.",
    "Отправьте список всех входящих переводов за последний месяц.",
    "Какие были мои последние пять транзакций?",
    "Сколько денег я потратил на коммунальные услуги за прошлый месяц?",
    "Есть ли у меня несанкционированные платежи?",
    "Могу ли я получить детализацию всех покупок за прошлую неделю?",
    "Проверьте, были ли возвраты средств на мой счет.",
    "Покажите историю всех переводов на мой счет за последние три месяца.",
    "Сколько денег было переведено на мой счет за последний год?",
    "Какие были мои последние транзакции с использованием мобильного приложения?",
    "Могу ли я узнать сумму всех транзакций по категории 'продукты' за текущий месяц?",
    "Проверьте, есть ли у меня незавершенные платежи.",
    "Покажите мне все мои траты на развлечения за последний месяц.",
    "Сколько денег было переведено с моего счета на благотворительность?",
    "Есть ли у меня активные подписки?",
    "Могу ли я получить отчёт по всем моим банковским счетам за текущий год?",
    "Покажите историю всех покупок, сделанных в интернете.",
    "Какие были мои последние транзакции по кредитной карте?",
    "Сколько я потратил на путешествия за последние полгода?",
    "Есть ли у меня необработанные переводы?",
    "Могу ли я узнать детали всех транзакций с определенным магазином?",
    "Проверьте статус всех моих переводов за последний месяц.",
    "Покажите мне историю всех входящих платежей за прошлый год.",
    "Сколько денег было списано на оплату налогов?",
    "Есть ли у меня просроченные переводы?",
    "Могу ли я узнать сумму всех покупок, сделанных за последние шесть месяцев?",
    "Проверьте, были ли возвраты на мой счет за последний месяц.",
    "Покажите историю всех моих переводов за последние два года.",
    "Сколько денег было потрачено на транспорт за текущий месяц?",
    "Какие были мои последние транзакции по дебетовой карте?",
    "Есть ли у меня активные платежные поручения?",
    "Могу ли я получить детализацию всех транзакций по категориям?",
    "Покажите мне все мои траты на здоровье и спорт.",
    "Сколько я потратил на одежду за последний месяц?",
    "Есть ли у меня незавершенные переводы?",
    "Покажите мне историю всех моих платежей за последние три года.",
    "Сколько денег было списано на оплату услуг связи?",
    "Какие были мои последние транзакции по PayPal?",
    "Есть ли у меня активные заказы на покупку валюты?",
    "Могу ли я узнать сумму всех переводов на определенный счет?",
]

write_dataset(train_dir, operation_dir, operations_requests)
write_dataset(train_dir, balance_dir, balance_requests)
write_dataset(test_dir, operation_dir, test_operations_dataset)
write_dataset(test_dir, balance_dir, test_balance_dataset)
