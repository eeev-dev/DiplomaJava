# Используем базовый образ Python
FROM python:3.9

# Устанавливаем необходимые пакеты для работы с DeepPavlov
RUN apt update && apt install -y build-essential git

# Устанавливаем DeepPavlov
RUN pip install deeppavlov

# Устанавливаем модель для русского языка
RUN python -m deeppavlov install squad_ru_bert

# Открываем порт, на котором будет работать сервер (5000 по умолчанию)
EXPOSE 5000

# Запуск API сервера
CMD ["python", "-m", "deeppavlov", "riseapi", "deeppavlov/configs/squad/squad_ru_bert.json"]
